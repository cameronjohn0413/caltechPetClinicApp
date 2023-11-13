import { Component, OnInit } from '@angular/core';
import { Client } from '../client';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators  } from '@angular/forms';
import { ClientsService } from '../clients.service';
import { Pet } from 'src/app/model/pet';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})

export class ClientsComponent implements OnInit {
  searchText?: string;
  clients: Array<Client> = [];
//  clientPets: Array<Pet> = [];
  searchClient: Client | undefined;
  clientForm!: FormGroup;
  petsArray!: FormArray;
  openCreate!: boolean;

  constructor(public clientService: ClientsService, public formBuilder: FormBuilder, private modalService:NgbModal) { }

  ngOnInit(): void {
      this.clientForm = this.formBuilder.group({
        id: [''],
        name: ['', Validators.required],
        email: ['', Validators.required],
        phone: ['', Validators.required],
        pets: this.formBuilder.array([
          this.formBuilder.group({
          id: [''],
          name: ['', Validators.required],
          age: ['', Validators.required],
          weight: ['', Validators.required],
          animalType: ['', Validators.required], 
          ownerId: [''] }),
        ])
        });

      this.loadAllClients();
  }

  get pets() {
    return this.clientForm.get("pets") as FormArray;
  }

  // Generate Empty Pet Form
  genRow(): FormGroup {
    return this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      age: ['', Validators.required],
      weight: ['', Validators.required],
      animalType: ['', Validators.required],
    });
  }

  // OnClick Create Client: Calls 'genRow()' and Adds Empty Pet Form to PetsArray Form Array
  addNewRow() {
    this.petsArray = this.clientForm.get('pets') as FormArray;
    this.petsArray.push(this.genRow());
  }

  // For UPDATE, Replicate New Row But With Actual Data
  genPet(clientOwnedPets: Pet): FormGroup {
    return this.formBuilder.group({
      id: [clientOwnedPets.id],
      name: [clientOwnedPets.name, Validators.required],
      age: [clientOwnedPets.age, Validators.required],
      weight: [clientOwnedPets.weight, Validators.required],
      animalType: [clientOwnedPets.animalType, Validators.required],
      ownerId: [clientOwnedPets.ownerId]
    });
  }

  addPets(clientOwnedPets: Pet) {
    this.petsArray = this.clientForm.get('pets') as FormArray;
    this.petsArray.push(this.genPet(clientOwnedPets));
  }

  removeRow(index: any) {
    this.petsArray = this.clientForm.get('pets') as FormArray;
    this.petsArray.removeAt(index);
  }

  // LOAD ALL CLIENTS - from shared Client Variable
  loadAllClients() {
    this.clientService.currentClients.subscribe({
      next:(result: any) => {
        this.clients = result;
        console.log(result);
      },
      error: (error: any) => {
        console.log(error);
      },
      complete: () => {
        console.log("Load All Clients Complete");
        console.log(this.clients);
      }
    });
  }

  // Get Client By ID
  getClientById(cid: any) {
    this.clientService.getClientById(cid).subscribe({
      next:(result: any) => {
        this.searchClient = result;
        console.log(result);
      },
      error: (error: any) => {
        console.log(error);
      },
      complete: () => {
        console.log("Client Loaded");
      }
    });
  }

  
  addClientDetails(addClient: any): void {
    this.clientForm.reset();
    this.pets.clear();
    this.openCreate = true;
    this.modalService.open(addClient, {size: "xl"});
    this.addNewRow();
  }

  // Create New Client
  createClient() {
    let newClient = this.clientForm.value;
    this.clientService.createClient(newClient).subscribe({
      next:(result: any) => {
        console.log(JSON.stringify(result));
      },
      error: (error: any) => {
        console.log(JSON.stringify(error));
      },
      complete: () => {
        console.log("Client Created");
      }
    });
    this.clientForm.reset();
    this.loadAllClients();
  }

  updateClientForm(formClient: Client, addClient: any) {
    this.pets.clear();
    formClient.pets?.forEach((p) => {
      this.addPets(p);
    });
    this.clientForm.patchValue({
      id: formClient.id,
      name: formClient.name,
      email: formClient.email,
      phone: formClient.phone
    });
    this.openCreate = false;
    this.modalService.open(addClient, {size: "xl"});
  }

  // Update Client | Create, Update, Delete Pets
  updateClient() {
    let updatedClient = this.clientForm.value;
    this.clientService.updateClient(updatedClient).subscribe({
      next:(result: any) => {
        console.log(result);
      },
      error: (error: any) => {
        console.log(error);
      },
      complete: () => {
        console.log("Client Created");
      }
    });
    this.clientForm.reset();
    this.loadAllClients();
  }

  // Delete Client | Deletes All Associated Pets
  deleteClient(cid: any) {
    this.pets.clear();
    this.clientService.deleteClient(cid).subscribe({
      next:(result: any) => {
        console.log(result);
      },
      error: (error: any) => {
        console.log(error);
      },
      complete: () => {
        console.log("Client Deleted");
      }
    });
    this.loadAllClients();
  }

}
