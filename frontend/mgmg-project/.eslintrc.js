module.exports = {
  env: {
    node: true,
    commonjs: true,
    browser: true,
    es2021: true,
  },
  extends: ["eslint:recommended", "plugin:vue/essential", "@vue/prettier"],
  overrides: [],
  parserOptions: {
    ecmaVersion: "latest",
    sourceType: "module",
  },
  plugins: ["vue"],
  rules: {
    "prettier/prettier": [
      "error",
      {
        trailingComma: "es5",
        singleQuote: true,
        semi: false,
        useTabs: true,
        tabWidth: 2,
        trailingComma: "all",
        printWidth: 200,
        bracketSpacing: true,
        bracketLine: true,
        vueIndentScriptAndStyle: true,
        rangeStart: 0,
        rangeEnd: Infinity,
      },
    ],
  },
};
