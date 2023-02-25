import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private REST_API_SERVER = "http://localhost:8080/api";

  constructor(private http: HttpClient) { }

  public getAllTarjetas(): Observable<any>{
    return this.http.get(this.REST_API_SERVER + "/tarjeta")
  }
  public getAllTransacciones(): Observable<any>{
    return this.http.get(this.REST_API_SERVER + "/transaccion")
  }
}
