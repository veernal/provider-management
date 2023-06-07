import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Provider } from '../payload/Provider';
import { ProviderserviceService } from '../service/providerservice.service';

@Component({
  selector: 'app-add-provider',
  templateUrl: './add-provider.component.html',
  styleUrls: ['./add-provider.component.scss']
})
export class AddProviderComponent implements OnInit {

  provider: Provider = {
    address: {
      id: 0,
      addressType: '',
      streetAddress: '',
      city:'',
      state:'',
      zip:''
    },
    license:{
      id:0,
      licenseNumber:0,
      licenseType:'',
      licenseVerified:'',
      agency:'',
      effectiveDate:''
    },

    id: 0,
    firstName:'',
    middleName:'',
    lastName:'',
    gender:'',
    dba:'',
    dob:'',
    classification:'',
    speciality:'',
    providerType:'',
    active:'',
    businessUserId:0,
    userId:'',

  }
 
  constructor(private service:ProviderserviceService ,public router:Router) { }

  ngOnInit(): void {
  }

  addProvider(){
    console.log(this.provider); 
   
   this.service.addProvider(this.provider.businessUserId, this.provider).subscribe({
    next: (res:any)=>{
      console.log("Provider added successfully" ,res);
      alert("Provider details added successfully")
      this.router.navigateByUrl('home');
  },
  error: (err:any)=>{
    console.log(err);
   
}
  })
  
  }

}
