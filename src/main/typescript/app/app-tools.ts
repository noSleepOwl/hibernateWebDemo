namespace Tools {
    export function guid(): string {
        function S4(): string {
            return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
        }

        return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
    }
    export function printf(str: [string]): string {
        let num = arguments.length;
        let oStr = arguments[0];
        for (let i = 1; i < num; i++) {
            let pattern = "\\{" + (i - 1) + "\\}";
            let re = new RegExp(pattern, "g");
            oStr = oStr.replace(re, arguments[i]);
        }
        return oStr;
    }
}