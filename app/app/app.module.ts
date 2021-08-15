import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRegister } from './register';
import { AppLogin } from './login';
import { FacebookDataService } from './facebookservice';
//import { HttpClient } from '@angular/common/http';
import { HttpClientModule }    from '@angular/common/http';

@NgModule({
  declarations: [  //register component
    AppComponent,AppRegister,AppLogin
  ],
  imports: [      //register non component ex pipe
    BrowserModule,HttpClientModule
  ],
  providers: [FacebookDataService],   //register services
  bootstrap: [AppComponent]  //starting component
})
export class AppModule { }
