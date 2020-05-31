import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Mapping } from '../_config/mapping';
import { UserService } from '../_services/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private userService: UserService) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (localStorage.getItem('accessToken') != null) {
      return true;
    } else {
      this.userService.signout('hack');
      this.router.navigate([Mapping.SIGNIN_URL]);
      return false;
    }
  }
}
