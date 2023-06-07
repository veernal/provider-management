import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BusinessUser } from '../payload/BusinessUser';
import { ProviderserviceService } from '../service/providerservice.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  users: BusinessUser =new BusinessUser();

  form: FormGroup = new FormGroup({
    userId: new FormControl(''),
    password: new FormControl(''),
  });
  providerserviceService: any;


  constructor(private service :ProviderserviceService, public router:Router,private formBuilder:FormBuilder ) {

    this.form = this.formBuilder.group(
      {
        userId: ['',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(20),
            // this.validateUsername.bind(this)
          ]
        ],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(40)
          ]
        ],
      },
    );

  }


  ngOnInit(): void {
  }

  register(){
    console.log("register started"+this.users);
    // if(this.users.customername)
    this.service.register(this.users).subscribe((data) => {
      (console.log(data));
      alert(data);
      this.router.navigateByUrl('/');
  }
    );
}

}