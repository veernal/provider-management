import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { BusinessUser } from '../payload/BusinessUser';
import { ProviderserviceService } from '../service/providerservice.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  users:BusinessUser =new BusinessUser();
  constructor(private service :ProviderserviceService, public router:Router,private formBuilder:FormBuilder) { }


  ngOnInit(): void {
  }

  login(){
    console.log("register started"+this.users);
    this.service.login(this.users).subscribe((data) => {
      console.log(data);
      alert(data);
      if(data=="User logged in successfully"){
      this.router.navigateByUrl('home');
      } else{
        alert("username or password doesn't exists");
      }
  }
    );
}

}
