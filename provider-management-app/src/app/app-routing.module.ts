import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AddProviderComponent } from "./add-provider/add-provider.component";
import { GetProviderComponent } from "./get-provider/get-provider.component";
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { SearchProviderComponent } from "./search-provider/search-provider.component";
import { UpdateProviderComponent } from "./update-provider/update-provider.component";



const routes: Routes = [
    { path:'home', component: HomeComponent},
    { path:'register', component: RegisterComponent},
    {path:'', component: LoginComponent},
    {path:'addProvider', component: AddProviderComponent},
    {path:'getProvider', component: GetProviderComponent},
    {path:'searchProvider', component: SearchProviderComponent},
    {path:'updateProvider', component: UpdateProviderComponent},
    
    
  ];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }