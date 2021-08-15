import { ApplicationRef,Component } from '@angular/core';
import { FacebookDataService } from './facebookservice';
import { FacebookUserModel } from './facebookuser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'facebook';
  
   private data:FacebookUserModel[];

   private data1:FacebookUserModel[];
   
    constructor(private uservice:FacebookDataService) {
       this.data = new Array<FacebookUserModel>(
        new FacebookUserModel("Rajesh", "Kayak", "abc@yahoo.com", "Bangalore"),
        new FacebookUserModel("Mohankumar", "Lifejacket", "mohan@gmail.com", "Hyderabad"),
        new FacebookUserModel("Apporva", "Soccer", "apporva@gmail.com", "Lucknow"),
        new FacebookUserModel("Ravi Kumar", "Corner", "ravi@gmail.com", "Chennai"),
        new FacebookUserModel("Anuradha", "Thinking", "anuradha@gmail.com", "Pune"));
    }
	
	getAllUser():FacebookUserModel[]{
		return this.data;
	}


  getalluserdetail(): void {
    this.uservice.getAllUserList()
      .then(todos1 => this.data1 = todos1 );  	
	  
	  console.log(this.data1);
  }
  
}
