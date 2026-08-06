import { Component } from '@angular/core';
import { DogGalleryComponent } from './dog-gallery';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { VisibilityDirective } from '../directive/visibility-directive';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'home',
  imports: [MatToolbarModule, MatIconModule, MatButtonModule, DogGalleryComponent, VisibilityDirective, RouterLink],
  templateUrl: './home.html',
  styleUrls: ['./home.css'],
})
export class HomeComponent {}
