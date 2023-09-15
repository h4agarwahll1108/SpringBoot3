import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private snackBar: MatSnackBar,private login:LoginService, private router: Router){}
  ngOnInit(): void {}

  loginData={
    username:'',
    password:''
  };
  

  formSubmit(){
    console.log("login btn clicked");
    if(this.loginData.username.trim()==''  || this.loginData.username==null){
      this.snackBar.open('username is required','',{
        duration: 30000,
      });
      return;

    }
    if(this.loginData.password.trim()==''  || this.loginData.password==null){
      this.snackBar.open('password is required','',{
        duration: 30000,
      });
      return;
    }
    //request to server to generate token
    this.login.generateToken(this.loginData).subscribe(
      (data:any)=>{
        console.log('success');
        console.log(data);
        //login...
        this.login.loginUser(data.token);
        this.login.getCurrentUser().subscribe(
          (user:any)=>{
            this.login.userDetail(user);
            console.log(user);
            //redirect...ADMIN: admin -dashboard
            //redirect...NORMAL: normal -dashboard
            if(this.login.getUserRole() == 'ADMIN'){
              //ADMIN DASHBOARD
              // window.location.href='/admin'
              this.router.navigate(['admin']);
              this.login.loginStatusSubject.next(true);

            } else if(this.login.getUserRole() == 'NORMAL'){
              //USER DASHBOARD
              // window.location.href='/user-dashboard'
              this.router.navigate(['user-dashboard']);
              this.login.loginStatusSubject.next(true);
            } else{
              this.login.logout();
            }
          });

      },
      (error)=>{
        console.log("Error!");
        console.log(error);
        this.snackBar.open('INVALID DETAILS! TRY AGAIN','',{
          duration: 30000,
        });
      }
      );
  }
}
