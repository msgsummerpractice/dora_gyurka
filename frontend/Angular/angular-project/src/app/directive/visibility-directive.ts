import { Directive, effect, inject, input, TemplateRef, ViewContainerRef } from '@angular/core';
import { AuthService } from '../service/auth-service';

@Directive({
  selector: '[checkVisibility]',
})
export class VisibilityDirective {
  private authService = inject(AuthService);
  private readonly _viewcontainer = inject(ViewContainerRef);
  private readonly _templateRef = inject(TemplateRef);

  private checkVisibility = this.authService.isAuthenticated();

  constructor() {
    effect(() => {
      if (this.checkVisibility) {
        this._viewcontainer.createEmbeddedView(this._templateRef);
      } else {
        this._viewcontainer.clear();
      }
    });
  }
}
