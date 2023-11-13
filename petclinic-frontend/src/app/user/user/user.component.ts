import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UserService } from '../user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit{

  searchText?: string;
  userForm!: FormGroup;
  users: Array<User> = [];
  openCreate!: boolean;

  constructor(private userService: UserService, public formBuilder: FormBuilder, private modalService:NgbModal) { }


  ngOnInit(): void {
      this.userForm = this.formBuilder.group({
        id: [],
        name: [],
        username: [],
        password: []
      });
      this.loadUsers();
  }

  loadUsers() {
    this.userService.getAllUsers().subscribe({
      next:(result:any) => {
        this.users = result;
      },
      error:(error:any) => {
        console.log(error);
      },
      complete:() => {
        console.log("LoadUsers Complete!");
      }
    })
  }

  openNewUser(userModal:any ) {
    this.openCreate = true;
    this.modalService.open(userModal, {size: "lg"});
  }

  newUser() {
    let thisUser = this.userForm.value;
    this.userService.createUser(thisUser).subscribe({
      next:(result:any) => {
        console.log(result);
      },
      error:(error:any) => {
        console.log(error);
      },
      complete:() => {
        console.log("New User Created Complete");
      }
    });
  }

  openUpdateUser(updateUser: User, userModal:any) {
    this.openCreate = false;
    this.userForm.patchValue({
      id: updateUser.id,
      name: updateUser.name,
      username: updateUser.username,
      password: updateUser.password
    });
    this.modalService.open(userModal, {size: "lg"});
  }

  // UPDATE USER as ADMIN
  updateUser() {
    let thisUser = this.userForm.value;
    this.userService.updateUserAdmin(thisUser).subscribe({
      next:(result:any) => {
        console.log(result);
      },
      error:(error:any) => {
        console.log(error);
      },
      complete:() => {
        console.log("Update User Complete");
      }
    });
  }

  deleteUser(uid:any) {
    this.userService.deleteUser(uid).subscribe({
      next:(result:any) => {
        console.log(result);
      },
      error:(error:any) => {
        console.log(error);
      },
      complete:() => {
        console.log("Delete User Complete");
      }
    });
  }

}
