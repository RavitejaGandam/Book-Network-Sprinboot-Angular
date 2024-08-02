import { Inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { TokenService } from '../token/token.service';

export const authGuard: CanActivateFn = () => {
  const tokenService = Inject(TokenService);
  const router = Inject(Router);
  if (tokenService.isTokenNotValid()) {
    router.navigate(['login']);
    return false;
  }
  return true;
};
