import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatUsername',
})
export class FormatUsernamePipe implements PipeTransform {
  transform(value: string): string {
    let newUsername = `${value.charAt(0).toLocaleUpperCase()}${value.slice(1).toLocaleLowerCase()}`;
    if(newUsername.length > 10) {
      return newUsername;
    }
    else {
      return newUsername.concat("@msgsummer");
    }
    
  }
}
