import { Component } from '@angular/core';
import { DogGalleryComponent } from './dog-gallery';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-adopt',
  imports: [MatToolbarModule,MatIconModule,MatButtonModule,DogGalleryComponent,RouterLink],
  templateUrl: './adopt.html',
  styleUrls: ['./adopt.css'],
})
export class Adopt {}
