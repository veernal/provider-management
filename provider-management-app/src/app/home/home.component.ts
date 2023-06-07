import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit{
  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  logout(){
    localStorage.clear();
    this.router.navigateByUrl('/');
  }

  longText = `Provider Management involves managing information 
  regarding healthcare providers. Currently, healthcare Insurance maintains 
  provider information manually and is seeking a web-based solution to replace this process. 
  The system’s online, real-time inquiry and update capabilities allow maintenance of and 
  access to critical provider information.`;

}
