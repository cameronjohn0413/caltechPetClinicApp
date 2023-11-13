import { Component, OnInit } from '@angular/core';
import { AppointmentsService } from '../appointments.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Appointment, PetClientAppt } from '../appointment';
import { Client } from 'src/app/clients/client';
import { ClientsService } from 'src/app/clients/clients.service';
import { ClientsComponent } from 'src/app/clients/clients/clients.component';
import { Pet } from 'src/app/model/pet';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})
export class AppointmentsComponent implements OnInit {

  
  searchText?: string;
  apptForm!: FormGroup;
  appts: Array<Appointment> = [];
  
  clientsArray: Array<Client> = [];
  dtoArray: Array<PetClientAppt> = [];
  
  openByCreate!: boolean;
  selectedClient!: Client;
  selectedPet!: Pet;

  currentClient!: any;
  currentPet!: any;

  constructor(public apptService: AppointmentsService, public clientService: ClientsService, public formBuilder: FormBuilder, private modalService:NgbModal) { 
    
  }

  ngOnInit(): void {
    this.apptForm = this.formBuilder.group({
      id: [''],
      scheduleDate: [''],
      petId: [''],
      reason: [''],
      meds: [''],
      note: [''],
      complete: [''],
    });
    this.loadAllAppts();
  }

  // printArray() {
  //   this.buildCombinedArray();
  //   console.log(this.dtoArray);
  //   console.log(this.clientsArray)
  // }

  onClientSelect(): void {
    console.log(this.selectedClient);
    this.selectedPet = {};
  }

  loadClients() {
    this.clientService.currentClients.subscribe({
      next:(result:any) => {
        this.clientsArray = result;
        this.buildCombinedArray();
      },
      error:(error:any) => {
        console.log("loadClients Erro:"+ error);
      },
      complete:() => {
        console.log("LoadClients Complete");
        
      }
    })
  }

  buildCombinedArray() {
    this.dtoArray = [];
     this.appts.forEach((a) => {
     let cReturn = this.clientsArray.filter((c) => {
      let mp = c.pets?.find((p) => a.petId === p.id)
      if (mp !== undefined) {
        let apptClientPet:PetClientAppt = {
            appointment: a,
            apptClient: c,
            apptPet: mp }
          this.dtoArray.push(apptClientPet);
        };
        return mp !== undefined;
      });
      console.log(cReturn);
     });
     console.log(this.dtoArray);
  }

  loadAllAppts() {
    this.apptService.loadAllAppointments().subscribe({
      next:(result: any) => {
        this.appts = result;
        console.log(result);
      },
      error:(error:any) => {
        console.log(error);
      },
      complete:() => {
        console.log("Load All Appointments Complete");
        this.loadClients();
      }
    });
  }
 
  // OPEN CREATE
  openCreate(apptModal: any): void {
    this.apptForm.reset();
    this.selectedClient = {};
    this.openByCreate = true;
    this.modalService.open(apptModal, {size: "lg"});
  }

  // CREATE
  createAppt() {
    let newAppt = this.apptForm.value;
    newAppt.petId = this.selectedPet.id;
    newAppt.complete = false;
    console.log(newAppt);
    this.apptService.createAppointment(newAppt).subscribe({
      next:(result:any) => {
        console.log(result);
      },
      error:(error:any) => {
        console.log(error);
      },
      complete:() => {
        console.log("Create Appointment Complete");
        this.apptForm.reset();
        this.loadAllAppts();
        this.buildCombinedArray();
      }
    });
    
  }

  // OPEN UPDATE
  openUpdate(apptCombine: PetClientAppt, apptModal: any): void {
    this.apptForm.reset();
    this.currentClient = apptCombine.apptClient.name;
    this.currentPet = apptCombine.apptPet.name;
    let dtime = apptCombine.appointment.scheduleDate;
    new DatePipe(dtime!);
    this.apptForm.patchValue({
      id: apptCombine.appointment.id,
      scheduleDate: apptCombine.appointment.scheduleDate,
      petId: apptCombine.appointment.petId,
      reason: apptCombine.appointment.reason,
      meds: apptCombine.appointment.meds,
      note: apptCombine.appointment.note,
      complete: apptCombine.appointment.complete
    });
    this.modalService.open(apptModal, {size: "lg"});
  }

  // UPDATE
  updateAppt() {
    let updatedAppt = this.apptForm.value;
    updatedAppt.complete == 1 ? updatedAppt.complete = true : updatedAppt.complete = false;
    this.apptService.updateAppointment(updatedAppt).subscribe({
      next:(result: any) => {
        console.log(result);
      },
      error:(error:any) => {
        console.log(error);
      },
      complete:() => {
        console.log("Update Appointment Complete");
        this.apptForm.reset();
      }
    });
    this.loadAllAppts();
    this.buildCombinedArray();
  }

  // DELETE
  deleteAppt(aid:any) {
    this.apptService.deleteAppointment(aid).subscribe({
      next:(result:any) => {
        console.log(result);
      },
      error:(error:any) => {
        console.log(error);
      },
      complete:() => {
        console.log("Delete Appointment Complete");
        this.loadAllAppts();
        this.buildCombinedArray();
      }
    });
    
  }


}
