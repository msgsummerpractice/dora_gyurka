import { HttpInterceptorFn } from '@angular/common/http';

export const httpInterceptor: HttpInterceptorFn = (req, next) => {
  const modifiedReq = req.clone({
    setHeaders: {
      Authorization: 'Bearer qertyhbn3456jlkl806vjv=',
    },
  });

  return next(modifiedReq);
};
