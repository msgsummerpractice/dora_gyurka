import { Component } from '@angular/core';
import { NotFoundComponent } from './not-found';
import { DogGalleryComponent } from './dog-gallery';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { VisibilityDirective } from '../directive/visibility-directive';

@Component({
  selector: 'home',
  imports: [
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    DogGalleryComponent,
    NotFoundComponent,
    VisibilityDirective,
  ],
  templateUrl: './home.html',
})
export class HomeComponent {}
