import { HttpHeaders } from '@angular/common/http';

export class Endpoint {
  public static get HTTP_OPTIONS_JSON(): {} {
    const headers: HttpHeaders = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    // headers.append('x-api-key', 'mYpsTaMURz2r8Cbgx2Tj01M2jMQbidZYapzkQxan');
    return {headers: headers};
  }
  public static get WEB_SERVICE_AUTHENTICATION(): string { return '/nginx_platform_be/aws/api/authentication/v1'; }
  // public static get WEB_SERVICE_AUTHENTICATION(): string { return 'https://o30re8bnd3.execute-api.eu-west-1.amazonaws.com/dev'; }
}
