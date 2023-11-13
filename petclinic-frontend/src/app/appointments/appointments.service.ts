import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Appointment } from './appointment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppointmentsService {

  private baseUrl: string = "http://18.222.162.102:8181/petclinic/appointment";

  constructor(public httpClient: HttpClient, public authService: AuthService) { }

  private loginHeader = this.authService.getHeaders();

  loadAllAppointments(): Observable<Appointment[]> {
    return this.httpClient.get<Appointment[]>(this.baseUrl + "/allAppointments", { headers:this.loginHeader });
  }

  getAppointmentById(aid: any): Observable<Appointment> {
    return this.httpClient.get<Appointment>(this.baseUrl + "/getApptById?cid="+ aid, { headers:this.loginHeader });
  }

  createAppointment(appt: Appointment): Observable<any> {
    return this.httpClient.post(this.baseUrl + "/newAppointment", appt, { headers:this.loginHeader, responseType:'text'});
  }

  updateAppointment(appt: Appointment): Observable<any> {
    return this.httpClient.put(this.baseUrl + "/updateAppointment", appt, {headers:this.loginHeader, responseType:'text'});
  }

  deleteAppointment(aid: any): Observable<any> {
    return this.httpClient.delete(this.baseUrl + "/deleteAppointment?aid="+ aid, {headers:this.loginHeader, responseType:'text'});
  }

}
