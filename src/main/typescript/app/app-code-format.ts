///<reference path="../gloable/gloable.d.ts"/>

namespace code_format {
    let static_field: {
        TYPE_HQL: 'hql'
    };

    function initContent(self: JQuery, language: string, code: string): void {
        let content = `<code class="hljs ${language}">${code}</code>`;
        self.html(content);
        $('code', self).each((index, block) => {
            hljs.highlightBlock(block);
        })
    }

    function format(): void {
        $('body pre[data-code-format]').each(function () {
            let self = $(this),
                language = <string>self.attr('data-code-format'),
                code = self.text().trim(),
                type = self.data('type'),
                showBack = <string | Function> self.data('showBack'),
                argumentNames = self.data('argumentNames');

            if (showBack && typeof showBack === 'string') {
                showBack = <Function>window[showBack];
            }

            initContent(self, language, code);
            if (type === static_field.TYPE_HQL) {
                self.wrap('<div class="col-sm-9"></div>')
                hqlShowBack(self, <Function>showBack, argumentNames, code);
            } else {
                self.wrap('<div class="col-sm-12"></div>')
            }
        })
    }

    function hqlShowBack(self: JQuery, showBack: Function, argumentNames: string, code: string): void {
        let inputs = "";
        if (argumentNames) {
            inputs = argumentNames.split(',').map(createInput).join("");
        }
        let form = createForm(inputs, code, self);
        let $form = $(form);
        $('button', $form).click(() => {
            submit($form, showBack);
        });
        self.parent().after(form);
    }

    function createInput(name: string): string {
        return `<div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">${name}:</label>
                        <div class="col-sm-10">
                            <input type="text" name="${name}" class="form-control" >
                        </div>
                    </div>`;
    }

    function createForm(inputs: string, code: string, codeContent: JQuery): string {
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
    }

    function submit(form: JQuery, back?: Function): void {
        $('form', form).ajaxSubmit(function (data: any): void {
            if (data && back && typeof back === 'function') {
                back(data);
            } else {
                showConsole(data);
            }
        })
    }

    function clearConsole(): void {
        $("#content").empty();
    }

    function showConsole(data: any): void {
        let str = '';
        if (typeof data === 'object') {
            str = JSON.stringify(data);
        }
        $('#content').append(`<div class="well">${str}</div>`)
    }
}



