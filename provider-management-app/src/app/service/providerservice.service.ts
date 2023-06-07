import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BusinessUser } from '../payload/BusinessUser';
import { Observable } from 'rxjs';
import { Provider } from '../payload/Provider';


const RegisterApi="http://localhost:9090/api/provider-management/businessUser/register";
const Login="http://localhost:9090/api/provider-management/businessUser/login";


@Injectable({
  providedIn: 'root'
})
export class ProviderserviceService {

  private search_url_prefix: string = "http://localhost:9090/api/provider-management/provider/search";

  constructor(private client:HttpClient) { }

  register(user :BusinessUser) :Observable<any>{
    const headers = new HttpHeaders()
      .set('content-type', 'application/json')
      .set('Access-Control-Allow-Origin', '*')
    console.log(user,JSON.stringify(user));
    return this.client.post(RegisterApi,user, {'headers': headers});
    
  }

  login(user:BusinessUser) {
    const headers = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*')
  return this.client.post(Login,user, {'headers': headers} );
  }

  
  addProvider(businessUserId:number, provider: Provider) : Observable<any>
  {
      return this.client.post("http://localhost:9090/api/provider-management/provider/"+businessUserId+"/addProvider",provider);
  }

  getProviders(): Observable<Provider[]>{
    return this.client.get<Provider[]>("http://localhost:9090/api/provider-management/provider/getAllProviders");
  }

  findAllProviders() {
    return this.client.get(this.search_url_prefix);
  }

  findProvidersByParams(keyword: string, anotherKeyword: string, classification: string, gender: string, active: string) {

    var url = this.search_url_prefix
    if (null != keyword || null != anotherKeyword || null != classification || null != gender ||null != active) {
      url = url + "?";
      if (keyword) {
        url = url + "keyword=" + keyword + "&";
      }
      if (anotherKeyword) {
        url = url + "anotherKeyword=" + anotherKeyword + "&";
      }
      if (classification) {
        url = url + "classification=" + classification + "&";
      }
      if (gender) {
        url = url + "gender=" + gender + "&";
      }
      if(active){
        url = url + "active=" + active + "&"
      }
      url = url.substring(0, url.length - 1);
    }
    console.log("constructed Search URL : " + url)
    return this.client.get(url);
  }


  updateProvider(provider :Provider,userId:String,providerId:number) :Observable<any>{
    console.log(provider,JSON.stringify(provider));
    return this.client.put("http://localhost:9090/api/provider-management/provider/"+userId+"/updateProvider/"+providerId,provider);
    
  }


  findProvider(providerId:number){
    return this.client.get("http://localhost:9090/api/provider-management/provider/findProviderById/"+providerId)
  }

  deleteProvider(userId:string, providerId: number){
    const headers = new HttpHeaders()
      .set('content-type', 'application/json')
      .set('Access-Control-Allow-Origin', '*')
    return this.client.delete("http://localhost:9090/api/provider-management/provider/deleteProvider/"+userId+"/"+providerId, {'headers': headers})

 
  }

  



}
