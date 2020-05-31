import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { TimeoutService } from '../_services/timeout.service';
import { Router } from '@angular/router';
import { Mapping } from '../_config/mapping';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.sass']
})
export class NavigationBarComponent implements OnInit {

  constructor(private timeoutService: TimeoutService,
    private userService: UserService,
    private router: Router) { }

  ngOnInit() {
    this.timeoutService.timeoutMethod();
  }

  goToHome(): void {
    this.router.navigate([Mapping.HOME_URL]);
  }

}
