import { Component, inject, OnInit, signal } from '@angular/core';
import { DogGalleryService } from '../service/dog-gallery';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';

@Component({
  selector: 'not-found',
  templateUrl: './not-found.html',
  imports: [MatIconModule, MatButtonModule, MatToolbarModule],
})
export class NotFoundComponent implements OnInit {
  dogGalleryService = inject(DogGalleryService);
  imageurl = signal('');

  ngOnInit() {
    this.dogGalleryService.getDogImages().subscribe((response) => {
      this.imageurl.set(response.message);
    });
  }
}
