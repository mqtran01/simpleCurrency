from urllib import request
import json


def get_currencies():
    try:
        currencies = []
        with open('./data/currencies.txt', 'r') as file:
            for line in file:
                data = line.split('|')
                if len(data[0]) != 3:
                    continue
                currencies.append(data[0])
    except OSError:
        currencies = ['File cannot be accessed']
    except IndexError:
        currencies = ['Data incorrect']
    return currencies


def update_cl(first='USD', second='USD'):
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
        with open('./data/currencies.txt', 'w') as file:
            file.write('timestamp=' + str(json_map['timestamp']) + '\n')
            for key in json_map['quotes']:
                file.write(key[3:] + '|' + str(json_map['quotes'][key]) + '\n')

        in_curr = json_map['quotes'][first]
        out_curr = json_map['quotes'][second]
        return in_curr, out_curr
    else:
        print("Nope")
    return None, None


if __name__ == '__main__':
    update_cl()
