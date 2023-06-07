import { Component, OnInit } from '@angular/core';
import { Provider } from '../payload/Provider';
import { ProviderserviceService } from '../service/providerservice.service';

@Component({
  selector: 'app-get-provider',
  templateUrl: './get-provider.component.html',
  styleUrls: ['./get-provider.component.scss']
})
export class GetProviderComponent implements OnInit {

  providers:Provider[]=[]
  constructor(private service:ProviderserviceService) { }

  ngOnInit(): void {
    this.service.getProviders().subscribe((data: Provider[]) => {
      console.log(data);
      this.providers = data;
  });
  }

  delete(provider: Provider){
    this.service.deleteProvider(provider.userId, provider.id).subscribe((data)=>{
      console.log(data);
      alert(data);
    });
  }

  deleteButton(provider: Provider){
    if(provider.active.toLowerCase() === 'yes'){
      return true;
    }else{
      return false;
    }
  }

}
