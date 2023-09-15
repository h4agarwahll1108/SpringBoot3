import { CanActivateFn } from '@angular/router';
import { LoginService } from './login.service';
import { inject } from '@angular/core';
import { Router } from '@angular/router';


export const adminGuard: CanActivateFn = (route, state) => {
  const login = inject(LoginService);
  const router = inject(Router);
  if(login.isLoggedIn() && login.getUserRole() == 'ADMIN'){
    return true;
  }

  router.navigate(['login']);

  return false;
};
