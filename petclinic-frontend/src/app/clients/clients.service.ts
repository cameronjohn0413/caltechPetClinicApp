import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { Client } from './client';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  private baseUrl: string = "http://localhost:8080/petclinic/client";
  private clientsSource = new BehaviorSubject<Client[]>([]);
  

  constructor(public httpClient: HttpClient, public authService: AuthService) { }
  
  get currentClients(): Observable<Client[]> {
    if (this.clientsSource.getValue().length === 0) {
      this.loadAllClients();
      
    }
      return this.clientsSource.asObservable();
  }
  
   private loginHeader = this.authService.getHeaders();

  // loadAllClients(): Observable<Client[]> {
  //   return this.httpClient.get<Client[]>(this.baseUrl + "/getClients", { headers:this.loginHeader });
  // }

  loadAllClients() {
    this.httpClient.get<Client[]>(this.baseUrl + "/getClients", { headers: this.loginHeader }).subscribe((result) => {
      this.clientsSource.next(result);
    });
    console.log("Client Service: HTTP GET Load Clients Called");
  }

  getClientById(cid: any): Observable<Client> {
    return this.httpClient.get<Client>(this.baseUrl + "/getClientById?cid="+ cid, { headers:this.loginHeader });
  }

  createClient(client: Client): Observable<any> {
    return this.httpClient.post(this.baseUrl + "/newClient", client, { headers:this.loginHeader, responseType:'text'});
  }

  updateClient(client: Client): Observable<any> {
    return this.httpClient.put(this.baseUrl + "/updateClientAndPets", client, {headers:this.loginHeader, responseType:'text'});
  }

  deleteClient(cid: any): Observable<any> {
    return this.httpClient.delete(this.baseUrl + "/deleteClient?cid="+ cid, {headers:this.loginHeader, responseType:'text'});
  }

}
