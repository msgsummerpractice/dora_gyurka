import { Component, inject } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { DogGalleryComponent } from './component/dog-gallery';

@Component({
  selector: 'app-root',
  imports: [MatToolbarModule, MatIconModule, MatButtonModule, DogGalleryComponent],
  templateUrl: './app.html',
  styleUrls: ['./app.css'],
})
export class App {}
