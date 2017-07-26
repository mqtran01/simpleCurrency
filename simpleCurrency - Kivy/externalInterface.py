from urllib import request
import json


def get_currencies():
    try:
        currencies = []
        with open('./data/currencies.txt', 'r') as file:
            for line in file:
                data = line.split('=')
                if len(data[0]) != 3:
                    continue
                currencies.append(data[0])
    except OSError:
        currencies = ['File cannot be accessed']
    except IndexError:
        currencies = ['Data incorrect']
    return currencies


def connect_cl():
    # Using urllib, can try requests after
    url = 'http://www.apilayer.net/api/live'
    access_key = '7ce86ab4c16f464ad90e91a858ae6977'
    conn = request.urlopen(url + '?access_key=' + access_key)

    if conn:
        print("Connected!")
        data = conn.read().decode()
        print(data)
        json_map = json.loads(data)
        print(json_map['quotes'])
        with open('./data/rates.txt', 'w') as file:
            file.write('timestamp=' + str(json_map['timestamp']) + '\n')
            for key in json_map['quotes']:
                file.write(key[3:] + '=' + str(json_map['quotes'][key]) + '\n')

    else:
        print("Nope")


if __name__ == '__main__':
    connect_cl()