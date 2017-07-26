import kivy
import externalInterface as ext

kivy.require('1.9.0')

from kivy.app import App
from kivy.uix.label import Label
from kivy.uix.widget import Widget
from kivy.uix.textinput import TextInput
from kivy.uix.gridlayout import GridLayout
from kivy.properties import ObjectProperty, ListProperty

class calcView(Widget):
    in_curr = ObjectProperty(None)
    out_curr = ObjectProperty(None)
    update_btn = ObjectProperty(None)
    in_choose = ObjectProperty(None)
    out_choose = ObjectProperty(None)

    def __init__(self, **kwargs):
        # self.currencies = ['AUD', 'USD', 'NZD']
        self.currencies = ext.get_currencies()
        super(calcView, self).__init__(**kwargs)
        # self.in_curr.text = "Hello"
        # self.in_choose.text = "Bye"



    # in_curr.text = "Hello world"


class viewApp(App):

    def build(self):
        return calcView()


if __name__ == '__main__':
    app = viewApp()
    app.run()
