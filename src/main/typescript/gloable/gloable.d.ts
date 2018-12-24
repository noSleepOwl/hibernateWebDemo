declare interface Page {
    name: string;
    path: string;
}

declare let webctx: string;
declare let examplePages: [Page];

declare interface JQuery {
    affix(arg: any): any;
}
