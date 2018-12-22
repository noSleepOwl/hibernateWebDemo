let code_format = {
    static_field: {
        TYPE_HQL: 'hql'
    },
    initContent: function (self, language, code) {
        let content = `<code class="hljs ${language}">${code}</code>`;
        self.html(content);
        $('code', self).each((index, block) => {
            hljs.highlightBlock(block);
        })
    },
    format: () => {
        $('body pre[data-code-format]').each(function () {
            let self = $(this),
                language = self.attr('data-code-format'),
                code = self.text(),
                type = self.data('type'),
                showBack = self.data('showBack'),
                argumentNames = self.data('argumentNames');
            if (showBack && typeof showBack === 'string') {
                showBack = window[showBack];
            }

            code_format.initContent(self, language, code);
            if (type === code_format.static_field.TYPE_HQL) {
                self.wrap('<div class="col-sm-9"></div>')
                code_format.hqlShowBack(self, showBack, argumentNames, code);
            } else {
                self.wrap('<div class="col-sm-12"></div>')
            }
        })
    },
    hqlShowBack: (self, showBack, argumentNames, code) => {
        let inputs = "";
        if (argumentNames) {
            inputs = argumentNames.split(',').map(code_format.createInput).join("");
        }

        let form = code_format.createForm(inputs, code, self);
        form = $(form);

        $('button', form).click(() => {
            // code_format.clearConsole();
            code_format.submit(form, showBack);
        });
        self.parent().after(form);
    },
    createInput: (name) => {
        return `<div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">${name}:</label>
                        <div class="col-sm-10">
                            <input type="text" name="${name}" class="form-control" >
                        </div>
                    </div>`;
    },
    createForm: (inputs, code, codeContent) => {
        let btn = 'btn btn-info pull-right';
        let btnContent = 'col-sm-offset-8 col-sm-4';
        let buttonStyle = '';
        if (!inputs) {
            btn = 'btn btn-info btn-sm btn-block pull-right';
            btnContent = 'col-sm-12';
            buttonStyle = `style="height: ${codeContent.outerHeight()}px;"`;
        }
        return `<div class="col-sm-3">
                <form class="form-horizontal" action="${webctx}hqlExample/paramQuery" method="post">
                <input type="hidden" name="hql" value="${code}" class="form-control" >
                       ${inputs}
                    <div class="form-group">
                        <div class="${btnContent}">
                            <button type="button" class="${btn}" ${buttonStyle}>运行</button>
                        </div>
                    </div>
                </form>
            </div>`;
    },
    submit: (form, back) => {
        $('form', form).ajaxSubmit(function (data) {
            if (data && back && typeof back === 'function') {
                back(data);
            } else {
                code_format.showConsole(data);
            }
        })
    },
    clearConsole: function () {
        $("#content").empty();
    },
    showConsole: function (data) {
        let str = '';
        if (typeof data === 'object') {
            str = JSON.stringify(data);
        }
        $('#content').append(`<div class="well">${str}</div>`)
    }
}


