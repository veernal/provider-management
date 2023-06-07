import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BusinessUser } from '../payload/BusinessUser';
import { Provider } from '../payload/Provider';
import { ProviderserviceService } from '../service/providerservice.service';

@Component({
  selector: 'app-update-provider',
  templateUrl: './update-provider.component.html',
  styleUrls: ['./update-provider.component.scss']
})
export class UpdateProviderComponent implements OnInit {

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

  //users:BusinessUser =new BusinessUser();
  
  
  constructor(private service:ProviderserviceService,public router:Router) { }

  ngOnInit(): void {
  }


  findProvider(){
    this.service.findProvider(this.provider.id).subscribe((data : any) =>{
      this.provider=data;
    })
  }

  update(){
    console.log("update started"+this.provider);
    this.service.updateProvider(this.provider,this.provider.userId,this.provider.id).subscribe((data) => {
      console.log(data);
      alert("Update Success")
      this.router.navigateByUrl('home');
  }
    );
}

}
