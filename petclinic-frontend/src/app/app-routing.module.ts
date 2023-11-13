import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { userGuard, adminGuard } from './auth/auth.guard';
import { UserComponent } from './user/user/user.component';
import { ClientsComponent } from './clients/clients/clients.component';
import { LogoutComponent } from './logout/logout.component';
import { AppointmentsComponent } from './appointments/appointments/appointments.component';


const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },

  { path: "home", component: HomeComponent, canActivate: [userGuard] },
  { path: "clients", component: ClientsComponent, canActivate: [userGuard] },
  { path: "appointments", component: AppointmentsComponent, canActivate: [userGuard] },
  { path: "users", component: UserComponent, canActivate: [adminGuard] },

  { path: "logout", component: LogoutComponent },
  // { path: "home", component: HomeComponent },
  // { path: "clients", component: ClientsComponent },
  // { path: "user", component: UserComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(
    routes,
    { onSameUrlNavigation: "reload" },
    ),
  ],
  exports: [
    RouterModule
  ],
})
export class AppRoutingModule { }
