import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { Router } from '@angular/router';
import { Mapping } from '../_config/mapping';
import { MatDialog } from '@angular/material';
import { StudentService } from '../_services/student.service';
import { UserInputPopUpComponent, UserInput } from './user-input-pop-up/user-input-pop-up.component';

@Component({
  selector: 'app-home',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.sass']
})
export class StudentComponent implements OnInit {
  userInput: string;

  constructor(private router: Router, private userService: UserService,
    private studentService: StudentService, public matDialog: MatDialog) { }

  ngOnInit() {
    this.userService.getUserRole();
  }

  goToSelectFile(): void {
    this.router.navigate([Mapping.SELECT_FILE_URL]);
  }

  userInputPopUp(): void {
    const dialogRef = this.matDialog.open(
      UserInputPopUpComponent, {
        width: '400px',
        height: '280px',
        data: { userInput: this.userInput }
      }
    );

    dialogRef.afterClosed().subscribe(
      result => {
        console.log('Dialog closed: userInput = ' + result);
        this.studentService.setUserInput(result);
      }
    );
  }

}
