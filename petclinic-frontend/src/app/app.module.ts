import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user/user.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClientJsonpModule } from '@angular/common/http';
import { LogoutComponent } from './logout/logout.component';
import { ClientsComponent } from './clients/clients/clients.component';
import { AppointmentsComponent } from './appointments/appointments/appointments.component';
import { FilterPipe } from './Features/filter.pipe';
import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS
} from '@angular/common/http';
import { AuthService } from './auth/auth.service';



@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    UserComponent,
    LogoutComponent,
    ClientsComponent,
    AppointmentsComponent,
    FilterPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    NgbModule,
  ],

  providers: [AuthService, { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
//  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
