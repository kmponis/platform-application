import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  constructor() { }

  setUserInput(userInput: string) {
    localStorage.setItem('userInput', userInput);
  }

  getUserInput(): string {
    return localStorage.getItem('userInput');
  }

  removeUserInput(): void {
    localStorage.removeItem('userInput');
  }

}
