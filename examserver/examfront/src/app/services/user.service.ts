import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private hhtp:HttpClient) { }


  // add user

  public addUser(user:any)
  {
    return this.hhtp.post(`${baseUrl}/user/`, user);
  }
}
