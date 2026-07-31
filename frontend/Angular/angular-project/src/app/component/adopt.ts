import { Component } from '@angular/core';
import { NotFoundComponent } from './not-found';
import { DogGalleryComponent } from './dog-gallery';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { VisibilityDirective } from '../visibility-directive';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-adopt',
  imports: [MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    DogGalleryComponent,
    NotFoundComponent,
    VisibilityDirective,
    RouterLink,],
  templateUrl: './adopt.html',
})
export class Adopt {}
