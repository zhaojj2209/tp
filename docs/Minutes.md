---
layout: page
title: Meeting Minutes
---

<ul>
  {% assign sorted_minutes = site.pages | sort: "date" | reverse %}
  {% for page in sorted_minutes %}
    {% if page.layout == "minutes" %}
      <li>
        <a href="{{ site.baseurl | append: page.url }}">Minutes for {{ page.date | date: "%-d %B %Y" }}</a>
      </li>
    {% endif %}
  {% endfor %}
</ul>
