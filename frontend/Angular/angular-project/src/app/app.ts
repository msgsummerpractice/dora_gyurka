import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
@Component({
  selector: 'app-root',
  imports: [MatToolbarModule, MatIconModule, MatButtonModule],
  templateUrl: './app.html',
  styleUrls: ['./app.css'],
})
export class App {}
