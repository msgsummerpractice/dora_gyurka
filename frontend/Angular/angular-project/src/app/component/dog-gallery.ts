import { Component, inject, OnInit, signal } from '@angular/core';
import { DogGalleryService } from '../service/dog-gallery';

@Component({
  selector: 'dog-gallery',
  templateUrl: './dog-gallery.html',
  styleUrls: ['./dog-gallery.css'],
})
export class DogGalleryComponent implements OnInit {
  private readonly dogGalleryService = inject(DogGalleryService);

  readonly imageurl1 = signal('');
  readonly imageurl2 = signal('');
  readonly imageurl3 = signal('');

  ngOnInit() {
    this.dogGalleryService.getDogImages().subscribe((response) => {
      this.imageurl1.set(response.message);
    });

    this.dogGalleryService.getDogImages().subscribe((response) => {
      this.imageurl2.set(response.message);
    });

    this.dogGalleryService.getDogImages().subscribe((response) => {
      this.imageurl3.set(response.message);
    });
  }
}
