import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  constructor(private userService:UserService,private snackBar: MatSnackBar) {}
  ngOnInit(): void {}

  public user ={
    username: '',
		password: '',
		fristName: '',
		lastName: '',
		email: '',
		phone: '',
  }

  formSubmit()
  {
    console.log(this.user);
    if(this.user.username =='' || this.user.username == null){
      // alert("user is required!!");
      this.snackBar.open('UserName is required!!', '',{
        duration:3000,
      });
      return;
    }
  //adduser: userservice
  this.userService.addUser(this.user).subscribe(
    (data:any) => { 
      //success
      console.log(data);
      // alert('success');
      Swal.fire('Successfully Dobe', 'user id is'+ data.id,'success');
    },
    (error) => {
      //error
      console.log(error);
      // alert('something went wrong');
      this.snackBar.open('something went wrong!!', '',{
        duration:3000,
      });
    }
    );
  
  }

}
