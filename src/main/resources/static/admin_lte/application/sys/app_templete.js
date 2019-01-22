let TemplateEngine = function (html, options) {

    let re = /<%([^%>]+)?%>/g, // 匹配模板
        // 匹配java 代码
        reExp = /(^( )?(if|for|else|switch|case|break|{|}))(.*)?/g,
        // js 代码部分
        code = 'var r=[];\n',
        cursor = 0,
        match = null,
        arg = options.data || "";

    function isCode(line) {
        return !!line.match(reExp);
    }

    function isNull(str) {
        return str && str !== ''
    }

    let add = function (line, js) {

        if (js) {
            if (isCode(line)) {
                code += line + '\n'
            } else {
                code += 'r.push(' + line + ');\n'
            }
        } else {
            if (isNull(line)) {
                code += 'r.push("' + line.replace(/"/g, '\\"') + '");\n';
            }
        }
        // js ? (code += line.match(reExp) ? line + '\n' : 'r.push(' + line + ');\n') :
        //     (code += line != '' ? 'r.push("' + line.replace(/"/g, '\\"') + '");\n' : '');
        return add;
    };

    // 查询 html中所有的表达式
    while (match = re.exec(html)) {
        //
        add(html.slice(cursor, match.index))(match[1], true);
        cursor = match.index + match[0].length;
    }
    add(html.substr(cursor, html.length - cursor));
    code += 'return r.join("");';
    // 生成 函数 , 替换
    return new Function(code.replace(/[\r\t\n]/g, '')).apply(options, arg);
};