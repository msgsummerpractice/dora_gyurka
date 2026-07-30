import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface DogResp {
  message: string;
  status: string;
}
@Injectable({
  providedIn: 'root',
})
export class DogGalleryService {
  constructor(private http: HttpClient) {}

  public getDogImages(): Observable<DogResp> {
    return this.http.get<DogResp>('https://dog.ceo/api/breeds/image/random');
  }
}
