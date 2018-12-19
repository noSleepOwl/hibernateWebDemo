let code_format = {
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
                code = self.text();
            code_format.initContent(self, language, code);
        })
    }
}

