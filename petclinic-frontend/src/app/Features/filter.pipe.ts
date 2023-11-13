import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'filter'
})
export class FilterPipe implements PipeTransform {
    transform(objs:any[], searchText: string): any[] {
        if (!objs) return [];
        if (!searchText) return objs;

        return objs.filter(obj => {
            return Object.keys(obj).some(key => {
                return String(obj[key]).toLowerCase().includes(searchText.toLowerCase());
            });
        });
    }

}