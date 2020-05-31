import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface UserInput {
  input: string;
}

@Component({
  selector: 'app-user-input-pop-up',
  templateUrl: './user-input-pop-up.component.html',
  styleUrls: ['./user-input-pop-up.component.sass']
})
export class UserInputPopUpComponent implements OnInit {

  constructor(public matDialogRef: MatDialogRef<UserInputPopUpComponent>,
    @Inject(MAT_DIALOG_DATA) public userInput: UserInput) { }

  ngOnInit() { }

  cancel(): void {
    this.matDialogRef.close();
  }

}
