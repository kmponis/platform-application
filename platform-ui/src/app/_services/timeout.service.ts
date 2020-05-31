import { Injectable } from '@angular/core';
import { Idle, DEFAULT_INTERRUPTSOURCES } from '@ng-idle/core';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class TimeoutService {

  constructor(private idle: Idle, private userService: UserService) { }

  timeoutMethod(): void {
    this.idle.setIdle(this.signinExpiresIn());
    this.idle.setTimeout(this.signinExpiresIn() + 5);
    this.idle.setInterrupts(DEFAULT_INTERRUPTSOURCES);

    this.idle.onTimeout.subscribe(() => {
      this.userService.signout('auto');
    });

    this.idle.watch();
  }

  signinExpiresIn(): number {
    if (localStorage.getItem('accessToken') && localStorage.getItem('sessionTimeOut')) {
      return +localStorage.getItem('sessionTimeOut');
    }
    return 0;
  }

}
