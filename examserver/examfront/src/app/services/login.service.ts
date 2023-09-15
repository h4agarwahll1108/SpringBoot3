import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject = new Subject<boolean>();

  constructor(private http:HttpClient) { }

  //current user which is logged in
  public getCurrentUser(){
    return this.http.get(`${baseUrl}/current-user`);
  }

  //generate-token
  public generateToken(loginData:any){
    return this.http.post(`${baseUrl}/generate-token`,loginData)

  }

  //Login user: set token in local storage
  public loginUser(token: string){
    localStorage.setItem('token',token);
    return true;
  }

  //islogin: user is logged in or not

  public isLoggedIn()
  {
    let tokenStr= localStorage.getItem('token')
    if(tokenStr == undefined || tokenStr == '' || tokenStr == null){
      return false;
    }
    else{
      return true;
    }
  }

  //Logout: remove token from local Storage

  public logout(){
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    return true;
  }

  //get token
  public getToken()
  {
    return localStorage.getItem('token')
  }

  //set userDetail

  public userDetail(user: any){
    localStorage.setItem('user',JSON.stringify(user));
  }

  //get user

  public getUser()
  {
    let userStr= localStorage.getItem('user')
    if(userStr != null){
      return JSON.parse(userStr);
    }
    else{
      this.logout();
      return null;
    }
  }

  //get user role

  public getUserRole()
  {
    let user = this.getUser();
    return user.authorities[0].authority;
  }
}
