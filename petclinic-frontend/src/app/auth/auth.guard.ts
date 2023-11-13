import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { inject } from '@angular/core';


// export const authGuard: CanActivateFn = (route, state) => {
//   return true;
// };

// Any Authenitaced User Route Guard
  export const userGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  let sessionUser = sessionStorage.getItem('user');
   const router = inject(Router);
  if (sessionUser == null) {
    return router.parseUrl('/login');
  } else {
    return true;
  }
}


// Admin Authenticated Route Guard 
 export const adminGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  let sessionUser = sessionStorage.getItem('user');
  if (sessionUser == 'admin') {
    return true;
  } else {
    return false;
  }
}
