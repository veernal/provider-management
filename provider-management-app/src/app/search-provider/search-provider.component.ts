import { CdkVirtualScrollableWindow } from '@angular/cdk/scrolling';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Provider } from '../payload/Provider';
import { ProviderserviceService } from '../service/providerservice.service';

@Component({
  selector: 'app-search-provider',
  templateUrl: './search-provider.component.html',
  styleUrls: ['./search-provider.component.scss']
})
export class SearchProviderComponent {
  searchForm: FormGroup;
  providers:Provider[]=[]


  constructor(private _searchService: ProviderserviceService, private router: Router) {

    this.findAllProviders();
    this.searchForm = new FormGroup({
      keyword: new FormControl([Validators.required]),
      anotherKeyword: new FormControl([Validators.required]),
      classification: new FormControl([Validators.required]),
      gender: new FormControl([Validators.required]),
      active: new FormControl([Validators.required])
  
    })
  }

  findProvidersByParams(keyword: string, anotherKeyword: string, classification: string, gender: string, active: string) {

    if (!keyword && !anotherKeyword && !classification && !gender && !active) {
      this.findAllProviders();
    } else {
      this._searchService.findProvidersByParams(keyword, anotherKeyword, classification, gender, active).subscribe({
        next: (res: any) => {
          this.providers = res;
          console.log(this.providers);
        },
        error: (err: any) => {
          console.log(err)
          alert(err)
        }
      })
    }
  }


  findAllProviders() {
    this._searchService.findAllProviders().subscribe({
      next: (res: any) => {
        if (!!res.statusCode) {
          alert(res.message)
        } else {
          this.providers = res;
          console.log(this.providers);
        }
      },
      error: (err: any) => {
        console.log(err)
      }
    })
  }

}
